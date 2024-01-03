package com.qinjinhui.paymentcredit.factory;


import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author qinjinhui
 * @Date 2023/12/27
 * @Describe
 **/
public class DefaultBusinessFactory implements BusinessFactory<BusinessType, CommonBusinessHandler> {

    private final Map<Object, Set<CommonBusinessHandler>> handlerMap = Maps.newHashMap();

    public DefaultBusinessFactory() {

    }

    @Override
    public void doRegister(BusinessType type, CommonBusinessHandler handler) {
        Set<CommonBusinessHandler> handlerSet = handlerMap.getOrDefault(type.getHandlerType(), Sets.newTreeSet());
        handlerSet.add(handler);
        handlerMap.put(type.getHandlerType(), handlerSet);
    }

    @Override
    public <R, S> List<S> doExecute(BusinessType type, R request) {
        Set<CommonBusinessHandler> handlerSet = handlerMap.get(type.getHandlerType());
        if (CollectionUtils.isEmpty(handlerSet)) {
            //抛出异常
            return null;
        } else {
            return (List) handlerSet.stream().map((handler) -> handler.execute(request)).collect(Collectors.toList());
        }
    }

    @Override
    public int size(BusinessType type) {
        Set<CommonBusinessHandler> handlerSet = handlerMap.get(type.getHandlerType());
        return handlerSet == null ? 0 : handlerSet.size();
    }
}
