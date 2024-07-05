package br.com.alura.FipTable.Service;

import java.util.List;

public interface IConvertData {
    <T> T getData(String json, Class<T> classe);

    <T> List<T> getList(String json, Class<T> classe);
}
