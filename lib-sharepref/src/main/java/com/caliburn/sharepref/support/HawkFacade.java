package com.caliburn.sharepref.support;

import java.util.Map;

interface HawkFacade {

    <T> void put(String key, T value);

    <T> T get(String key);

    <T> T get(String key, T defaultValue);

    <T> void putObj(String key, T value);

    <T> Object getObj(String key, T defaultValue);

    Map<String, ?> getAll();

    long count();

    void deleteAll();

    void delete(String key);

    boolean contains(String key);

    boolean isBuilt();

    void destroy();

    class EmptyHawkFacade implements HawkFacade {

        @Override
        public <T> void put(String key, T value) {
            throwValidation();
        }

        @Override
        public <T> T get(String key) {
            throwValidation();
            return null;
        }

        @Override
        public <T> T get(String key, T defaultValue) {
            throwValidation();
            return null;
        }

        @Override
        public <T> void putObj(String key, T value) {
            throwValidation();
        }

        @Override
        public <T> Object getObj(String key, T defaultValue) {
            throwValidation();
            return null;
        }

        @Override
        public Map<String, ?> getAll() {
            throwValidation();
            return null;
        }

        @Override
        public long count() {
            throwValidation();
            return 0;
        }

        @Override
        public void deleteAll() {
            throwValidation();
        }

        @Override
        public void delete(String key) {
            throwValidation();
        }

        @Override
        public boolean contains(String key) {
            throwValidation();
            return false;
        }

        @Override
        public boolean isBuilt() {
            return false;
        }

        @Override
        public void destroy() {
            throwValidation();
        }

        private void throwValidation() {
            throw new IllegalStateException("Hawk is not built. " +
                    "Please call build() and wait the initialisation finishes.");
        }
    }
}
