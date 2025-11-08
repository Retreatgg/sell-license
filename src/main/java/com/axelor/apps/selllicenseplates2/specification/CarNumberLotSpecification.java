package com.axelor.apps.selllicenseplates2.specification;

import com.axelor.apps.selllicenseplates2.model.CarNumberLot;
import org.springframework.data.jpa.domain.Specification;

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
}
