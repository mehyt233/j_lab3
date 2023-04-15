package com.jva;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Consumer;

/**
 * Класс, тестирующий разные списки на различные операции, задаваемые пользователем
 */
public class ListTester {

    private List list;

    /**
     * Инициализирует тестер заданным списком
     * @param listToTest тип задаваемого списка
     */
    public ListTester(Class<? extends List> listToTest) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        list = listToTest.getDeclaredConstructor().newInstance();
    }

    /**
     * замеряет среднюю скорость выполнения метода
     * @param count количество выполнений
     * @param initialState устанавливает начальное состояние списка перед тестированием
     * @param function выполняемый метод
     * @return среднее время выполнения метода в наносекундах
     */
    public double testSpeed(int count, Consumer<List> initialState, Consumer<List> function) {

        clearList();
        initialState.accept(list);
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            function.accept(list);
        }
        long finish = System.nanoTime();
        return (finish-start)/(double)count;
    }

    /**
     * очищает список
     */
    private void clearList(){
        list.clear();
    }
}
