package com.axelor.apps.selllicenseplates2.specification;

import com.axelor.apps.selllicenseplates2.enums.CarNumberCategory;
import com.axelor.apps.selllicenseplates2.model.CarNumberLot;
import org.springframework.data.jpa.domain.Specification;

import static com.axelor.apps.selllicenseplates2.enums.CarNumberCategory.IDENTICAL_NUMBERS;

public class CarNumberLotSpecification {

    public static Specification<CarNumberLot> orderByPriceAsc() {
        return (root, query, criteriaBuilder) -> {
            assert query != null;
            query.orderBy(criteriaBuilder.asc(root.get("markupPrice")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<CarNumberLot> orderByPriceDesc() {
        return (root, query, criteriaBuilder) -> {
            assert query != null;
            query.orderBy(criteriaBuilder.desc(root.get("markupPrice")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<CarNumberLot> orderByCreatedDateDesc() {
        return (root, query, criteriaBuilder) -> {
            assert query != null;
            query.orderBy(criteriaBuilder.desc(root.get("createdDate")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<CarNumberLot> orderByCreatedDateAsc() {
        return (root, query, criteriaBuilder) -> {
            assert query != null;
            query.orderBy(criteriaBuilder.asc(root.get("createdDate")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<CarNumberLot> orderByAuthorFullNameAsc() {
        return (root, query, criteriaBuilder) -> {
            assert query != null;
            query.orderBy(criteriaBuilder.asc(root.get("author").get("fullName")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<CarNumberLot> orderByAuthorFullNameDesc() {
        return (root, query, criteriaBuilder) -> {
            assert query != null;
            query.orderBy(criteriaBuilder.desc(root.get("author").get("fullName")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<CarNumberLot> hasIdenticalNumbers(Boolean identicalLetters) {
        if (identicalLetters == null || !identicalLetters) {
            return (r, q, cb) -> cb.conjunction();
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("firstDigit"), root.get("secondDigit")),
                        criteriaBuilder.equal(root.get("firstDigit"), root.get("thirdDigit"))
                );
    }

    public static Specification<CarNumberLot> isDeleted(Boolean isDeleted) {
        return (r, q, cb) -> {
            if (isDeleted == null) return cb.conjunction();
            return cb.equal(r.get("isDeleted"), isDeleted);
        };
    }

    public static Specification<CarNumberLot> isSold(Boolean isSold) {
        return (r, q, cb) -> {
            if (isSold == null) return cb.conjunction();
            return cb.equal(r.get("isSold"), isSold);
        };
    }

    public static Specification<CarNumberLot> hasRegion(Long regionId) {
        return (r, q, cb) -> {
            if (regionId == 0) return cb.conjunction();
            return cb.equal(r.get("region").get("id"), regionId);
        };
    }

    public static Specification<CarNumberLot> hasIdenticalLetters(Boolean identicalNumbers) {
        if(identicalNumbers == null || !identicalNumbers) {
            return (r, q, cb) -> cb.conjunction();
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("firstLetter"), root.get("secondLetter")),
                        criteriaBuilder.equal(root.get("firstLetter"), root.get("thirdLetter"))
                );
    }

    public static Specification<CarNumberLot> orderByCreatedDate(String sort) {
        if("date_desc".equals(sort)) {
            return orderByCreatedDateAsc();
        } else {
            return orderByCreatedDateDesc();
        }
    }

    public static Specification<CarNumberLot> isConfirm(Boolean isConfirm) {
        return (r, q, cb) -> {
            if (isConfirm == null) return cb.conjunction();
            return cb.equal(r.get("isConfirm"), isConfirm);
        };
    }

    public static Specification<CarNumberLot> byAuthorEmail(String email) {
        return (r, q, cb) -> {
            if (email == null) return cb.conjunction();
            return cb.equal(r.get("author").get("email"), email);
        };

    }

    public static Specification<CarNumberLot> hasCategory(CarNumberCategory category) {
        return (root, query, cb) -> {
            if (category == null) {
                return cb.conjunction();
            }

            switch (category) {
                case IDENTICAL_NUMBERS:
                    return cb.and(
                            cb.equal(cb.substring(root.get("number"), 1, 1), cb.substring(root.get("number"), 2, 1)),
                            cb.equal(cb.substring(root.get("number"), 2, 1), cb.substring(root.get("number"), 3, 1))
                    );

                case IDENTICAL_LETTERS:
                    return cb.and(
                            cb.equal(cb.substring(root.get("series"), 1, 1), cb.substring(root.get("series"), 2, 1)),
                            cb.equal(cb.substring(root.get("series"), 2, 1), cb.substring(root.get("series"), 3, 1))
                    );

                case FIRST_TEN:
                    return cb.between(root.get("number"), "001", "010");

                case ROUND:
                    return cb.and(
                            cb.like(root.get("number"), "%00"),
                            cb.notEqual(root.get("number"), "000")
                    );

                case MIRROR:
                    return cb.and(
                            cb.equal(cb.substring(root.get("number"), 1, 1), cb.substring(root.get("number"), 3, 1)),
                            cb.notEqual(cb.substring(root.get("number"), 1, 1), cb.substring(root.get("number"), 2, 1))
                    );

                default:
                    return cb.conjunction();
            }
        };
    }
}
