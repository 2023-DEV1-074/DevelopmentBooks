package com.bnpp.fortis.developmentbooks.service.impl;

import com.bnpp.fortis.developmentbooks.exception.InvalidBookException;
import com.bnpp.fortis.developmentbooks.exception.InvalidQuantityException;
import com.bnpp.fortis.developmentbooks.model.BookCartDto;
import com.bnpp.fortis.developmentbooks.model.BookGroupClassification;
import com.bnpp.fortis.developmentbooks.model.CartSummaryReportDto;
import com.bnpp.fortis.developmentbooks.service.PriceSummationService;
import com.bnpp.fortis.developmentbooks.storerepository.BookStoreEnum;
import com.bnpp.fortis.developmentbooks.storerepository.DiscountDetailsEnum;
import com.bnpp.fortis.developmentbooks.utils.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceSummationServiceImpl implements PriceSummationService {

    @Override
    public CartSummaryReportDto getCartSummaryReport(List<BookCartDto> bookCartDtoList) {

        validateAllBooks(bookCartDtoList);
        Map<String, Integer> listOfBooksWithQuantityMap = bookCartDtoList.stream()
                .collect(Collectors.toMap(BookCartDto::getName, BookCartDto::getQuantity));
        List<Integer> listOfPossibleDiscounts = getPossibleDiscountValues(listOfBooksWithQuantityMap.size());
        CartSummaryReportDto cartSummaryReportDto = new CartSummaryReportDto();

        if (CollectionUtils.isNotEmpty(listOfPossibleDiscounts)) {
            calculateAndUpdatePriceWithDiscount(listOfBooksWithQuantityMap, listOfPossibleDiscounts, cartSummaryReportDto);
        } else {
            calculateAndUpdatePriceWithOutDiscount(listOfBooksWithQuantityMap, cartSummaryReportDto);
        }

        return cartSummaryReportDto;
    }
    public void calculateAndUpdatePriceWithOutDiscount(Map<String, Integer> listOfbooksWithQuantityMap, CartSummaryReportDto priceSummaryDto) {
        BookGroupClassification booksWithoutDiscount = getListOfBookGroupWithoutDiscount(listOfbooksWithQuantityMap);
        List<BookGroupClassification> listOfBookGroupClassification = new ArrayList<>();
        listOfBookGroupClassification.add(booksWithoutDiscount);
        updateBestDiscount(priceSummaryDto, listOfBookGroupClassification);
    }

    public void calculateAndUpdatePriceWithDiscount(Map<String, Integer> listOfbooksWithQuantityMap, List<Integer> listOfPossibleDiscounts, CartSummaryReportDto cartSummaryReportDto) {
        listOfPossibleDiscounts.stream().forEach(numberOfBooksToGroup -> {
            Map<String, Integer> bookQuantityMapCopy = duplicateMap(listOfbooksWithQuantityMap);
            List<BookGroupClassification> listOfBookGroupClassification = getListOfBookGroupWithDiscount(bookQuantityMapCopy, new ArrayList<BookGroupClassification>(), numberOfBooksToGroup);
            if (CollectionUtils.isNotEmpty(bookQuantityMapCopy.keySet())) {
                BookGroupClassification booksWithoutDiscount = getListOfBookGroupWithoutDiscount(bookQuantityMapCopy);
                listOfBookGroupClassification.add(booksWithoutDiscount);
            }
            updateBestDiscount(cartSummaryReportDto, listOfBookGroupClassification);
        });
    }

    private List<BookGroupClassification> getListOfBookGroupWithDiscount(Map<String, Integer> listOfBooksWithQuantityMap,
                                                                         List<BookGroupClassification> bookGroupClassificationList,int numberOfBooksToGroup) {
        numberOfBooksToGroup = getNumberOfBooksToGroup(listOfBooksWithQuantityMap,numberOfBooksToGroup);
        Optional<DiscountDetailsEnum> discount = getDiscount(numberOfBooksToGroup);
        if (discount.isPresent()) {
            int bookGroupSize = discount.get().getNumberOfDistinctItems();
            List<String> listOfDistinctBooks = listOfBooksWithQuantityMap.keySet().stream().limit(bookGroupSize)
                    .collect(Collectors.toList());
            BookGroupClassification currentBookGroup = getBookGroup(listOfDistinctBooks);
            bookGroupClassificationList.add(currentBookGroup);
            removeDiscountedBooksFromMap(listOfBooksWithQuantityMap, listOfDistinctBooks);
            getListOfBookGroupWithDiscount(listOfBooksWithQuantityMap, bookGroupClassificationList, numberOfBooksToGroup);
        }
        return bookGroupClassificationList;
    }

    private BookGroupClassification getListOfBookGroupWithoutDiscount(Map<String, Integer> listOfBooksWithQuantityMap) {
        Map<String, Double> bookIdPriceMap = getBookNamePriceMap();
        Set<String> bookTitles = listOfBooksWithQuantityMap.keySet();
        double actualPrice = bookTitles.stream()
                .mapToDouble(bookId -> bookIdPriceMap.get(bookId) * listOfBooksWithQuantityMap.get(bookId)).sum();
        int numberOfBooks = listOfBooksWithQuantityMap.values().stream().mapToInt(Integer::intValue).sum();
        return new BookGroupClassification(new ArrayList<>(listOfBooksWithQuantityMap.keySet()), Constants.ZERO_PERCENT, actualPrice, Constants.NO_DISCOUNT, numberOfBooks);
    }

    private Map<String, Double> getBookNamePriceMap() {
        return Arrays.stream(BookStoreEnum.values())
                .collect(Collectors.toMap(BookStoreEnum::getBookTitle, BookStoreEnum::getPrice));
    }

    private BookGroupClassification getBookGroup(List<String> listOfBookToGroup) {
        Map<String, Double> bookNamePriceMap = getValidBooks();
        double actualPrice = listOfBookToGroup.stream().mapToDouble(bookName -> bookNamePriceMap.get(bookName))
                .sum();
        int discountPercentage = getDiscountPercentage(listOfBookToGroup.size());
        double discount = (actualPrice * discountPercentage) / Constants.HUNDRED;
        return new BookGroupClassification(listOfBookToGroup, discountPercentage, actualPrice, discount, listOfBookToGroup.size());

    }

    private Map<String, Double> getValidBooks() {
        return Arrays.stream(BookStoreEnum.values())
                .collect(Collectors.toMap(BookStoreEnum::getBookTitle, BookStoreEnum::getPrice));
    }

    private Optional<DiscountDetailsEnum> getDiscount(int numberOfBooks) {

        return Arrays.stream(DiscountDetailsEnum.values()).filter(discountGroup -> discountGroup.getNumberOfDistinctItems() == numberOfBooks).findFirst();
    }


    private void removeDiscountedBooksFromMap(Map<String, Integer> listOfbookWithQuantityMap, List<String> discountedBooks) {
        discountedBooks.forEach(bookName -> {
            int quantity = listOfbookWithQuantityMap.get(bookName);
            if (quantity > Constants.ONE_QUANTITY) {
                listOfbookWithQuantityMap.put(bookName, quantity - Constants.ONE_QUANTITY);
            } else {
                listOfbookWithQuantityMap.remove(bookName);
            }
        });
    }

    private int getDiscountPercentage(int numberOfDistinctBooks) {

        Optional<DiscountDetailsEnum> checkDiscount = getDiscount(numberOfDistinctBooks);

        return (checkDiscount.isPresent()) ? checkDiscount.get().getDiscountPercentage() : Constants.ZERO_PERCENT;
    }

    private List<Integer> getPossibleDiscountValues(int numberOfBooks) {

        return Arrays.stream(DiscountDetailsEnum.values()).sorted(Comparator.reverseOrder()).filter(discountGroup -> discountGroup.getNumberOfDistinctItems() <= numberOfBooks).map(DiscountDetailsEnum::getNumberOfDistinctItems).collect(Collectors.toList());
    }
    private void updateBestDiscount(CartSummaryReportDto priceSummaryDto, List<BookGroupClassification> listOfBookGroupClassification) {
        double discount = listOfBookGroupClassification.stream().mapToDouble(BookGroupClassification::getDiscountAmount).sum();
        if (discount >= priceSummaryDto.getTotalDiscount()) {
            priceSummaryDto.setListOfBookGroupClassifications(listOfBookGroupClassification);
            double actualPrice = listOfBookGroupClassification.stream().mapToDouble(BookGroupClassification::getActualPrice).sum();
            priceSummaryDto.setActualPrice(actualPrice);
            priceSummaryDto.setTotalDiscount(discount);
            priceSummaryDto.setCostEffectivePrice(actualPrice - discount);
        }
    }
    private Map<String, Integer> duplicateMap(Map<String, Integer> listOfBooksWithQuantityMap) {

        return listOfBooksWithQuantityMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (firstKey, secondKey) -> secondKey, LinkedHashMap::new));
    }

    private int getNumberOfBooksToGroup(Map<String, Integer> listOfBooksWithQuantityMap, Integer numberOfBooksToGroup) {
        return numberOfBooksToGroup < listOfBooksWithQuantityMap.size() ? numberOfBooksToGroup : listOfBooksWithQuantityMap.size();
    }

    public void validateAllBooks(List<BookCartDto> bookCartDtoList) {
        Map<String, Double> validBooks = getValidBooks();
        List<String> invalidBooks = bookCartDtoList.stream().filter(book -> !validBooks.containsKey(book.getName())).map(BookCartDto::getName).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(invalidBooks)) {
            throw new InvalidBookException(invalidBooks);
        }
        List<Integer> invalidQuantities = bookCartDtoList.stream().filter(book -> book.getQuantity() <= 0 ).map(BookCartDto::getQuantity).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(invalidQuantities)) {
            throw new InvalidQuantityException(invalidQuantities);
        }
    }
}