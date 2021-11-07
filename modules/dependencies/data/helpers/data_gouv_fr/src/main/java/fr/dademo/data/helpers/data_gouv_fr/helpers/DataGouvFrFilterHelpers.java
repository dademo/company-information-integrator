package fr.dademo.data.helpers.data_gouv_fr.helpers;

import fr.dademo.data.definitions.data_gouv_fr.dimensions.DataGouvFrDataSetResource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataGouvFrFilterHelpers {

    public static <R extends String> Predicate<DataGouvFrDataSetResource> fieldStartingWith(Function<? super DataGouvFrDataSetResource, ? extends R> getter, R prefix) {
        return dataGouvFrDataSetResource -> getter.apply(dataGouvFrDataSetResource).startsWith(prefix);
    }

    public static <R extends String> Predicate<DataGouvFrDataSetResource> fieldEndingWith(Function<? super DataGouvFrDataSetResource, ? extends R> getter, R suffix) {
        return dataGouvFrDataSetResource -> getter.apply(dataGouvFrDataSetResource).endsWith(suffix);
    }

    public static <R extends String> Predicate<DataGouvFrDataSetResource> fieldContaining(Function<? super DataGouvFrDataSetResource, ? extends R> getter, R s) {
        return dataGouvFrDataSetResource -> getter.apply(dataGouvFrDataSetResource).contains(s);
    }

    public static <R extends String> Predicate<DataGouvFrDataSetResource> fieldMatching(Function<? super DataGouvFrDataSetResource, ? extends R> getter, R regex) {
        return dataGouvFrDataSetResource -> getter.apply(dataGouvFrDataSetResource).matches(regex);
    }

    public static <R extends String> Predicate<DataGouvFrDataSetResource> fieldEquals(Function<? super DataGouvFrDataSetResource, ? extends R> getter, R value) {
        return dataGouvFrDataSetResource -> getter.apply(dataGouvFrDataSetResource).equals(value);
    }
}
