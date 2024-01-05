package com.qinjinhui.paymentcredit;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qinjinhui.paymentcredit.core.baseFrom.BaseFrom;
import com.qinjinhui.paymentcredit.dao.PayAccount;
import com.qinjinhui.paymentcredit.entity.Person;
import com.qinjinhui.paymentcredit.entity.Person1;
import com.qinjinhui.paymentcredit.service.ExcelImpl;
import com.qinjinhui.paymentcredit.service.TestServiceImpl;
import com.qinjinhui.paymentcredit.utils.Color;
import com.qinjinhui.paymentcredit.utils.GenerateTableUtils;
import com.qinjinhui.paymentcredit.utils.Java8future;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class PaymentCreditApplicationTests {
    static {

    }

    @Autowired
    GenerateTableUtils generateTableUtils;
    @Autowired
    TestServiceImpl testService;
    @Autowired
    private MockMvc mockMvc;
    @Value("#{${app:{'JDJQ':'001'}}}")
    private Map<String, Integer> map;

    public static void fun(Consumer<Integer> consumer, Integer age) {
        consumer.accept(age);
    }

    public static void fun(Supplier<Integer> supplier) {
        Integer integer = supplier.get();
        System.out.println(integer);
    }

    // 通用方法：创建对象并设置对象值
    public static <T> T createObject(Supplier<T> supplier, Object... values) {
        Class<?>[] parameterTypes = Arrays.stream(values).map(Object::getClass).toArray(Class[]::new);
        Constructor<T> constructor = (Constructor<T>) supplier.get().getClass().getDeclaredConstructors()[0];
        try {
            return constructor.newInstance(Arrays.copyOf(values, constructor.getParameterCount()));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    // 通用方法：使用方法引用设置对象值
    public static <T, U> void setObjectValue(Consumer<U> consumer, U value) {
        consumer.accept(value);
    }

    @Test
    void contextLoads() throws Exception {
        BaseFrom build = BaseFrom.builder().token("000").build();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/test")
                .accept(MediaType.APPLICATION_JSON)
                // post请求的请求内容的 mediaType
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJsonStr(build));

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                // 设置期望的结果,用 ResultMatcher 来表示
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 内置的打印mock请求结果
                .andDo(MockMvcResultHandlers.print())
                // 正式执行接口,并返回接口的返回值
                .andReturn();
    }

    @Test
    public void validationTest() {

//        List<PayAccount> payAccounts = testService.getMapper().baseSelect(PayAccount.class);
//        generateTableUtils.generateTable(PaymentExecuteConfig.class);

        //int[] my_Array; my_Array=new int[5]; for(int count=0;count<=5;count++) System.out.println(my_Array[count]);

        List<Integer> integerList = Arrays.asList(1, 3, 2);
//方法一，强制转换
        Integer[] integerAry = (Integer[]) integerList.toArray();

    }

    /**
     * 消费型接口
     */
    @Test
    public void functionTest() {
        fun((t) -> {
            System.out.println(t);
        }, 18);
    }

    /**
     * 供给型接口
     */
    @Test
    public void supplierTest() {
        fun(() -> 20);
    }

    @Test
    public <K, V extends Comparable<? super V>> void functionTest1() {
        LinkedList<Object> objects = Lists.newLinkedList();
        objects.add("HL_C");
        objects.add("C");
        objects.add("D");
        objects.add("A_C");
        HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("D", 1);
        objectObjectHashMap.put("HL_C", 2);
        objectObjectHashMap.put("HL_H", 3);
        objectObjectHashMap.put("H", 4);
        objectObjectHashMap.put("C", 5);
        objectObjectHashMap.put("A", 6);
        Map<K, V> objectObjectLinkedHashMap = Maps.newHashMap();
        objects.forEach(ob -> objectObjectHashMap.entrySet().removeIf(objectObjectEntry -> !objectObjectEntry.getKey().equals(ob)));
        objects.forEach(ob -> objectObjectHashMap.forEach((key, value) -> {
            if (key.equals(ob)) {
                objectObjectLinkedHashMap.put((K) key, (V) value);
            }
        }));
        System.out.println("======================" + objectObjectLinkedHashMap);
        Map<K, V> kvMap = Java8future.sortByValue(objectObjectLinkedHashMap, false);
        System.out.println("======================" + kvMap);
    }

    @Test
    public void riskRouteResultTest() {
        class Solution {
            public int[] twoSum(int[] nums, int target) {
                int n = nums.length;
                for (int i = 0; i < n; ++i) {
                    for (int j = i + 1; j < n; ++j) {
                        if (nums[i] + nums[j] == target) {
                            return new int[]{i, j};
                        }
                    }
                }
                return new int[0];
            }
        }

        String[] testA = new String[]{"C", "HL"};
        String[] testB = new String[]{"D", "HL", "HL_C", "A", "HH"};
        String testC = "";
        for (int i = 0; i < testA.length; ++i) {
            for (int j = i + 1; j < testB.length; ++j) {
                if (testA[i] == testB[j]) {
                    testC = testB[j];
                }
            }
        }
    }

    public <R, T> String routeConfig(Function<R, String> function, R item) {
        String apply = function.apply(item);
        return apply;
    }

    @Test
    public void valueMapTest() {
        System.out.println("===================" + map);
    }

    @Test
    public void doWhileTest() {

        AtomicReference<String>[] aa = new AtomicReference[]{new AtomicReference<>("100")};
        AtomicReference<String> bb = new AtomicReference<>("200");
        AtomicReference<String> cc = new AtomicReference<>("300");
        IntStream.range(0, 100).filter(i -> {
            if (i < 20) return true;
            System.out.println(i);
            aa[0] = i > 21 ? bb : cc;
            return false;
        }).findAny();
        System.out.println(aa[0].get());
    }

    @Test
    public void doWhileTest1() {
        int[] test1 = new int[]{2, 7, 11, 15};
        int test2 = 9;
        twoSum(test1, test2);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    @Test
    public void lamdbaStringTest() {
        String str = "apple";
        List<String> list = Arrays.stream(str.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void lamdbaListTest() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);

        Stream<Integer> stream1 = list1.stream();
        Stream<Integer> stream2 = list2.stream();

        List<Integer> result = stream1.filter(e -> !stream2.collect(Collectors.toList()).contains(e))
                .collect(Collectors.toList());

        System.out.println(result);  // [1, 2]
    }

    @Test
    public void fileSplitterTest() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
//        File file = new File("E:\\qinjinhui\\banner.txt");
////        int numThreads = 4;
////        int chunkSize = 1024 * 1024; // 1MB chunk size
////        int bufferSize = 8192; // 8KB buffer size
////
////        FileDataImporter splitter = new FileDataImporter(chunkSize, bufferSize,numThreads);
////        splitter.importFileData(file);

        Path filePath = Paths.get("E:\\qinjinhui\\banner.txt");
        long lineCount = Files.lines(filePath).count();
        System.out.println(lineCount);

    }

    @Test
    public void groupTest() {
        List<Integer> list = IntStream.rangeClosed(0, 1000).boxed().collect(Collectors.toList());

        int batchSize = 100;
//        List<Pair<Integer, Integer>> result = IntStream.range(0, list.size())
//                .boxed()
//                .collect(Collectors.groupingBy(i -> i / batchSize))
//                .map(group -> {
//                    int start = group.get(0);
//                    int end = group.get(group.size() - 1) + 1;
//                    return new Pair<>(start, end);
//                })
//                .collect(Collectors.toList());
//        result.forEach(System.out::println);
//        Pair<Integer, Integer> integerIntegerPair = result.get(0);
//        Integer key = integerIntegerPair.getKey();
    }

    @Test
    public void taaa() {
        IntStream.range(0, 1000)
                .boxed()
                .collect(Collectors.groupingBy(i -> i / 100))
                .forEach((k, v) -> {
                    int start = v.get(0) + 1;
                    int end = v.get(v.size() - 1) + 1;
                    System.out.println("第 " + (k + 1) + " 组：开始数：" + start + "，结束数：" + end);
                });

        // result.forEach(System.out::println);
    }

    @Test
    public void replace() {
        String replaceTest1 = "102223-19";
        PayAccount build = PayAccount.builder().accounttype(replaceTest1.replace("-", "000")).build();
        System.out.println(build);
    }

    @Test
    public void bigDecimal() {
        AtomicReference<BigDecimal> bigDecimal = new AtomicReference<>(BigDecimal.ZERO);
        System.out.println("开始Bigde" + bigDecimal.get());
        for (int i = 0; i < 100; i++) {
            bigDecimal.set(bigDecimal.get().add(new BigDecimal(i)));
        }
        System.out.println("结束Bigde" + bigDecimal.get());
    }

    @Test
    public void bigdecimalTest() {
        AtomicReference<String>[] array = Stream.generate(() -> new AtomicReference<>("1")).limit(2).toArray(AtomicReference[]::new);
        System.out.println(array[0]);
        System.out.println(array[1]);
        System.out.println(array[2]);
    }

    @Test
    public void biConsumer() {
        // 使用构造方法引用创建对象并设置对象值
        Person person = (Person) createObject(Person::new, "John", 30);

        // 使用方法引用设置对象值
        setObjectValue(person::setName, "Mary");

        // 使用方法引用设置对象值
        setObjectValue(person::setAge, 25);
    }

    public void aaa(Supplier<Person> supplier, Consumer<Person> consumer) {
        Person person = supplier.get();
        consumer.accept(person);
        System.out.println(person.toString());
    }

    @Test
    public void supplierTest1() {
        aaa(Person::new, per -> {
            per.setAge(22);
            per.setName("bbbb");
        });
    }

    @Test
    public void parallelTest() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // 串行流
        int sum1 = Arrays.stream(array)
                .filter(x -> x % 2 == 0) // 筛选偶数
                .map(x -> x * x) // 求平方
                .sum(); // 求和

        // 并行流 TODO:作用大数据计算速度会很快，并行流的作用是利用多核 CPU 资源，将任务划分为多个子任务并行处理，提高处理速度。
        int sum2 = Arrays.stream(array)
                .parallel() // 并行流
                .filter(x -> x % 2 == 0) // 筛选偶数
                .map(x -> x * x) // 求平方
                .sum(); // 求和

        System.out.println("串行流结果：" + sum1);
        System.out.println("并行流结果：" + sum2);
    }


    @Test
    public void intTest() {
        int a = 10;
        int b = 20;
        int result = Integer.compare(a, b);
        if (result < 0 && result == 1 && result == 2) {
            System.out.println("a is less than b");
        } else if (result == 0) {
            System.out.println("a is equal to b");
        } else {
            System.out.println("a is greater than b");
        }
    }

    @Test
    public void aaa() {
        List<Person1> list = new ArrayList<>();
        list.add(new Person1("A"));
        list.add(new Person1("B"));
        list.add(new Person1("C"));
        list.add(new Person1("A"));
        list.add(new Person1("D"));
        list.add(new Person1("B"));
        System.out.println(list.size());
// 使用并行流处理重复数据并重新赋值
        list.parallelStream()
                .collect(Collectors.groupingByConcurrent(Person1::getName)).values().forEach(objects -> {
            if (objects.size() > 1) {
                objects.parallelStream().forEach(obj -> obj.setAge(11));
            }
        });

        // 输出处理后的列表
        list.forEach(System.out::println);
        System.out.println(list.size());
    }

    @Test
    public void personalTest() {
        Person1 xa = new Person1("张三");
        System.out.println(xa);
    }

    @Test
    public void personalTest2() {
        Map<String, String> map = new HashMap<>();
        map.put("abbd", "value1");
        map.put("bbb", "value2");
        map.put("ddd", "value3");
        map.put("cccc", "value4");

        List<String> list = Arrays.asList("cccc", "ddd", "bb");

        // 筛选出与List重复的相同数据
        Map<String, String> result = map.entrySet().stream()
                .filter(entry -> list.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 输出结果
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    @Test
    public void colorTest() {
        Color color = Color.RED;

        switch (color) {
            case RED:
                // 处理红色
                break;
            case GREEN:
                // 处理绿色
                break;
            case BLUE:
                // 处理蓝色
                break;
            default:
                // 处理其他情况
                break;
        }

    }

    @Test
    public void ruleMapTest(){
        // 给定的规则
        Map<String, Integer> ruleMap = new HashMap<>();
        ruleMap.put("H_D", 1);
        ruleMap.put("HL_C",2);
        ruleMap.put("HL_H", 3);
        ruleMap.put("H", 4);
        ruleMap.put("C", 5);

        // 给定的数据
        List<String> dataList = Arrays.asList("HL_C", "C", "D");

        // 根据数据查找对应的 value 值，并构建列表
        List<Integer> values = dataList.stream()
                .map(ruleMap::get)
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.toList());

        // 根据排序后的 value 值查找对应的 key 值，得到排序后的规则列表
        List<String> sortedRuleList = values.stream()
                .map(value -> getKeyByValue(ruleMap, value))
                .collect(Collectors.toList());

        // 输出排序后的规则
        System.out.println("排序后的规则：" + sortedRuleList);
    }

    // 根据 value 查找对应的 key
    private static String getKeyByValue(Map<String, Integer> map, int value) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue() == value)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }


    @Test
    public void containsTest() {
        List<aaa> listOfLists =new ArrayList<>();
    }

    @Data
    class aaa {
        List<Integer> bb;
    }
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void scanControllerURLs(){
        RequestMappingHandlerMapping handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();
            Class<?> controllerClass = handlerMethod.getBeanType();

            // 判断是否有@Controller注解
            if (AnnotatedElementUtils.hasAnnotation(controllerClass, Controller.class)) {
                // 获取@Controller注解的value，通常是Controller的URL前缀
                String baseUrl = getBaseUrl(controllerClass);

                // 获取方法上的@RequestMapping注解
                Method method = handlerMethod.getMethod();
                RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);

                if (methodMapping != null) {
                    String[] methodUrls = methodMapping.value();

                    for (String methodUrl : methodUrls) {
                        // 拼接baseUrl和方法的URL
                        String fullUrl = baseUrl + methodUrl;
                        System.out.println("URL: " + fullUrl);
                    }
                }
            }
        }
    }
    private String getBaseUrl(Class<?> controllerClass) {
        RequestMapping classMapping = AnnotatedElementUtils.findMergedAnnotation(controllerClass, RequestMapping.class);
        if (classMapping != null) {
            return String.join("", classMapping.value());
        }
        return "";
    }

    @Test
    public void test() {
        Class<?>[] classs = ClassUtil.getClasses("com.qinjinhui.paymentcredit.controller");
        List<String> list = new ArrayList<>();
        for (Object clazz : classs) {
            String url = "";
            Class<?> tt = (Class<?>) clazz;
            PostMapping annotation = tt.getAnnotation(PostMapping.class);
            String[] value = annotation.value();
            Method[] methods = tt.getDeclaredMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation1 : annotations) {
                    String[] value1 = (String[]) ReflectUtil.invoke(annotation1, "value");
                    url = value[0] + value1[0];
                    list.add(url);
                }
            }
        }
        CollectionUtil.sortByPinyin(list);
        System.out.println(JSONUtil.toJsonStr(list));
        list.forEach(x -> {
            System.out.println(x);
        });
    }

//    @Test
//    public void testToMap() {
//        Collection<Person> collection = new Collection<Person>();
//        Set<Person> set = LamdbaUtils.toSet(collection);
//
//        Map<Integer, Person> integerPersonMap = LamdbaUtils.toMap(set, Person::getAge);
//    }
//
//    @Test
//    public void testToMapV2() {
//        Collection<Person> collection = coll;
//        Set<Person> set = toSet(collection);
//
//        Map<Long, Double> map = toMap(set, OrderItem::getOrderId, OrderItem::getActPrice);
//    }

    @Test
    public  void entrtyTest() {
        BaseFrom bu = BaseFrom.builder().token("第一次").build();
        System.out.println(bu);
        bu.builder().token1("第二次添加").build();
        System.out.println(bu);
    }

//    public static void main(String[] args) {
//        BaseFrom bu = BaseFrom.builder().token("第一次").build();
//        System.out.println(bu);
//        bu.setToken1("第二次添加");
//        System.out.println(bu);
//        bu.builder().token1("第三次").build();
//        System.out.println(bu);
//    }




//        public static void main(String[] args) {
//            MyObject obj1 = new MyObject(Arrays.asList("a", "b", "c"));
//            MyObject obj2 = new MyObject(null);
//            MyObject obj3 = null;
//
//            Set<String> result1 = processObject(obj1);
//            Set<String> result2 = processObject(obj2);
//            Set<String> result3 = processObject(obj3);
//
//            System.out.println("Result 1: " + result1);
//            System.out.println("Result 2: " + result2);
//            System.out.println("Result 3: " + result3);
//        }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("00","01", "02", null);
        Set<String> setA= Sets.newHashSet();
        setA.add("00");
        setA.add(null);
        System.out.println("set"+ setA);
        setA.removeAll(list);
        System.out.println("set"+ setA);

    }
        public static Set<String> processObject(MyObject myObject) {
            return Optional.ofNullable(myObject)
                    .map(MyObject::getSomeList)
                    .map(someList -> someList.stream().collect(Collectors.toSet()))
                    .orElse(Collections.emptySet());
        }

    static class MyObject {
        private List<String> someList;

        public MyObject(List<String> someList) {
            this.someList = someList;
        }

        public List<String> getSomeList() {
            return someList;
        }
    }


    @Autowired
    ExcelImpl excel;

    @Test
    public void excelImplTest(){
        excel.tttt();
    }



}