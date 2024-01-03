package com.qinjinhui.paymentcredit.factory;

import com.alipay.sofa.common.utils.StringUtil;
import com.alipay.sofa.common.xmap.Context;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author qinjinhui
 * @Date 2024/1/3
 * @Describe
 **/
public class FlowUtil {


    public static <T> T getTopContextValue(Context topContext,String key,Class<T> clazz) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        return null;
    }

    public static <T extends AbstractStandardContext> T wrapFlowContext(Context topContext, AbstractStandardContext originFlowContext, Class<T> newContextClass) {
        if (topContext == null || newContextClass == null) {

        }
        T flowContext;
        if (originFlowContext != null && newContextClass.isInstance(originFlowContext)) {
            flowContext = (T) originFlowContext;
        } else {
            if (originFlowContext != null) {
                //FlowUtil.
            }
            flowContext = FlowUtil.getTopContextValue(topContext,"",newContextClass);
        }
        return flowContext;
    }

    public static Class getContextClass(Class sourceClass, Class targetClass, int index) {
        Class currentClass = sourceClass;
        Class contextClass = null;
        while (true) {
            if (Object.class.equals(currentClass)) {
                throw new RuntimeException(String.format("Target CLASS [{}] is not in the super class of [{}]",targetClass,sourceClass));
            }
            Type extendType = currentClass.getGenericSuperclass();
            Class extendClass = getRawClass(extendType);
            if (targetClass.equals(extendClass)) {
                contextClass = getRawClass(((ParameterizedType)extendType).getActualTypeArguments()[index]);
            }
            if (contextClass != null) {
                break;
            }
            currentClass = extendClass;
        }
        return contextClass;
    }

    private static Class getRawClass(Type type) {
        if (ParameterizedType.class.isInstance(type)) {
            return (Class) ((ParameterizedType) type).getRawType();
        } else if (Class.class.isInstance(type)) {
            return ((Class) type);
        }
        throw new RuntimeException("TYPE");
    }

    public static <C extends AbstractStandardContext> Class<C> getContextClass(Class aClass, Class targetClass) {
        return getContextClass(aClass,targetClass,0);
    }
}
