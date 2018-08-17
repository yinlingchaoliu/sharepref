package com.caliburn.sharepref.support;

class DataInfo {

    public static final char TYPE_OBJECT = '0';
    public static final char TYPE_LIST = '1';
    public static final char TYPE_MAP = '2';
    public static final char TYPE_SET = '3';

    private char dataType;
    private String cipherText;
    private Class keyClazz;
    private Class valueClazz;

    DataInfo(char dataType, String cipherText, Class keyClazz, Class valueClazz) {
        this.cipherText = cipherText;
        this.keyClazz = keyClazz;
        this.valueClazz = valueClazz;
        this.dataType = dataType;
    }

    public char getDataType() {
        return dataType;
    }

    public void setDataType(char dataType) {
        this.dataType = dataType;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public Class getKeyClazz() {
        return keyClazz;
    }

    public void setKeyClazz(Class keyClazz) {
        this.keyClazz = keyClazz;
    }

    public Class getValueClazz() {
        return valueClazz;
    }

    public void setValueClazz(Class valueClazz) {
        this.valueClazz = valueClazz;
    }
}
