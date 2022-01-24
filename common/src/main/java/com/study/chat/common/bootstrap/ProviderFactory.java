package com.study.chat.common.bootstrap;

public interface ProviderFactory<T extends Provider> {
        T newProvider();
}
