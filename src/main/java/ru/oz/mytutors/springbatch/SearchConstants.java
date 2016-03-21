package ru.oz.mytutors.springbatch;

/**
 * Created by ozol on 18.03.2016.
 */
public final class SearchConstants {

    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";
    public static final String REGION_PICKUP_AVAILABLE = "region_pickup_available";
    public static final String REGION_DELIVERY_AVAILABLE = "region_delivery_available";
    public static final String CATEGORY_URI = "category_uri";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_LINK = "category_link";
    public static final String CATEGORY_NAME = "category_name";
    public static final String CATEGORIES = "categories";
    public static final String BRAND_ID = "brand_id";
    public static final String BRAND_NAME = "brand_name";
    public static final String DESCRIPTION = "description";
    public static final String PROPERTY_AGGREGATE = "property_aggregate";
    public static final String NAME = "name";
    public static final String FULL_NAME = "full_name";
    public static final String PROPERTIES = "properties";
    public static final String OPTIONAL_PROPERTY_PREFIX = "facet_"; // todo public static final String OPTIONAL_PROPERTY_PREFIX = "property_prefix";

    public static final String BASESTORE = "basestore";
    public static final String PRICE = "price";
    public static final String PRICE_ID = "price_id";
    public static final String OLD_PRICE = "old_price";
    public static final String BESTSELLER = "bestseller";
    public static final String PICKUP_TODAY = "pickup_today";
    public static final String DELIVERY_DATE = "delivery_date";
    public static final String NEXT_DELIVERY_DATE = "next_delivery_date";
    public static final String UNAVAILABLE_BASESTORES = "unavailable_basestores";
    public static final String FILTERS = "filters";
    public static final String RATING = "rating";

    public static final String OUR_OFFERS = "Our Offers";
    public static final String BRANDS = "Brands";
    public static final String RATINGS = "Ratings";
    public static final String PRICES = "Prices";

    public static final String PICTURE = "picture";
    public static final String SMALL_PICTURE = "small_picture";
    public static final String PATH = "path";
    public static final String FT_SEARCHABLE = "ft_searchable";

    public static final String SORT = "sort";
    public static final String SORT_BY = "sort_by";
    public static final String ACTIVE = "active";
    public static final String VISIBLE = "visible";
    public static final String ONLY_FIRST = "only_first";
    public static final String COUNT_FIRST = "count_first";
    public static final String RANGE = "range";
    public static final String MULTI_VALUE = "multi_value";
    public static final String GROUP_ID = "group_id";
    public static final String ATTR_CODE = "attr_code";
    public static final String ATTRS = "attrs";
    public static final String MIN = "min";
    public static final String MAX = "max";

    public static final String SYNONYMS = "synonyms";
    public static final String DM_CAPTION = "dm_caption";
    public static final String ACTIVATION_TIME = "activation_time";
    public static final String UPDATE_INDEX_TYPE = "update_index_type";
    public static final String IMAGE_URL = "image_url";
    public static final String BASESTORES_WITH_PRICE = "basestores_with_price";
    public static final String PRODUCT_WEIGHT = "product_weight";
    public static final String CATEGORY_WEIGHT = "category_weight";
    public static final String TOTAL_WEIGHT = "total_weight";
    public static final String WEIGHT = "weight";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String BODY = "body";
    public static final String TYPE = "type";

    private SearchConstants() {
        //no instance available
    }

    public static final class SphinxSearch {
        // show meta
        public static final String VALUE = "Value";
        public static final String VARIABLE_NAME_COLUMN = "Variable_name";
        public static final String KEYWQRD = "keyword";
        public static final String HITS = "hits";
        public static final String TOTAL_FOUND = "total_found";

        public static final String COUNT = "count(*)";

        public static final String PRODUCT_SEARCH_FIELD = "full_name ";
        public static final String CATEGORY_SEARCH_FIELD = "category_name ";
        public static final String BRAND_SEARCH_FIELD = "brand_name ";

        public static final String STRING = "string";
        public static final String BIGINT = "bigint";
        public static final String MULTI = "multi";
        public static final String FLOAT = "float";
        public static final String BOOL = "bool";
        public static final String TIMESTAMP = "timestamp";

        public static final String JSON = "json";

        public static final String SRV_PRODUCT_INDEX = "srv_product_rt";
        public static final String SRV_PRICE_INDEX = "srv_price_rt";
        public static final String SRV_CONTENT_INDEX = "srv_content_rt";
        public static final String SRV_CATEGORY_INDEX = "srv_category_rt";

        public static final String FACET_VALUE_TABLE = "facet_value";
        public static final String FACET_TABLE = "facet";

//        public static final String SRV_PRODUCT_INDEX = "srv_product_index";
//        public static final String SRV_PRICE_INDEX = "srv_price_index";

        private SphinxSearch() {
        }
    }

    public static final class EmbeddedBd {
        public static final class SavedSearchRequestsTable {
            public static final String TABLE_NAME = "SEARCH_PHRASE";
            public static final String ID = "id";
            public static final String TEXT = "text";
            public static final String COUNT = "count";
            public static final String WEIGHT = "weight";
            public static final String URI = "uri";

        }

        public static final class TemporarySearchRequestsTable {
            public static final String TABLE_NAME = "SEARCH_PHRASE_TMP";
            public static final String REQUEST = "text";
            public static final String TIMESTAMP = "timestamp";
        }

        public static final class SynonymsTable {
            public static final String TABLE_NAME = "synonyms";
            public static final String ID = "id";
            public static final String WORD = "word";
            public static final String SYNONYMS = "synonyms";
            public static final String SOUNDEX_CODES = "soundex_codes";
            public static final String ACTIVITY = "activity";
        }

        public static final class StopWordsTable {
            public static final String TABLE_NAME = "stop_words";
            public static final String ID = "id";
            public static final String WORD = "word";
            public static final String ACTIVITY = "activity";
        }

        public static final class AutocompleteTable {
            public static final String TABLE_NAME = "autocomplete";
            public static final String WORD = "word";
        }

        public static final class DictionaryTable {
            public static final String TABLE_NAME = "DICTIONARY";
            public static final String ID = "id";
            public static final String TEXT = "text";
            public static final String SOUNDEX = "soundex";
        }


        public static final class CityTable {
            public static final String TABLE_NAME = "city";
            public static final String ID = "id";
            public static final String NAME = "name";
        }
    }

}