package com.caliburn.sharepref.support;

interface Serializer {

    <T> String serialize(String cipherText, T value);

    DataInfo deserialize(String plainText);
}