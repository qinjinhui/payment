//package com.qinjinhui.paymentcredit.factory;
//
//
//import com.alipay.sofa.common.xmap.Context;
//
///**
// * @Author qinjinhui
// * @Date 2024/1/3
// * @Describe  标准上下文处理器适配
// **/
//public abstract class AbstractStandardContextHandlerWrapper<C extends AbstractStandardContext,T,S>
//        extends AbstractBusinessHandler<T,S> {
//
//    private Class<C> contextClass;
//
//    protected  C getActualFlowContext(Context topContext, AbstractStandardContext originFlowContext) {
//        Class targetClass = getContextTargetClass();
//        Class<C> contextClass = getContextClass(targetClass);
//        return FlowUtil.wrapFlowContext(topContext,originFlowContext,contextClass);
//    }
//
//    protected abstract Class getContextTargetClass();
//
//    protected Class<C> getContextClass(Class targetClass) {
//        if (contextClass == null) {
//            synchronized (this) {
//                if (contextClass == null) {
//                    contextClass = FlowUtil.getContextClass(getClass(),targetClass);
//                }
//            }
//        }
//        return contextClass;
//    }
//}
