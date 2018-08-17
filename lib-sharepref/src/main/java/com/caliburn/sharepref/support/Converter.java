package com.caliburn.sharepref.support;

interface Converter {

    <T> String toString(T value);

    <T> T fromString(String value, DataInfo dataInfo) throws Exception;

}
