package com.iseelotus.zuulserver.filters;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FilterUtils {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String ACCOUNT_ID = "tmx-account-id";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";

    public String getCorrelationId() {
        RequestContext rqc = RequestContext.getCurrentContext();

        if (rqc.getRequest().getHeader(CORRELATION_ID) != null) {
            return rqc.getRequest().getHeader(CORRELATION_ID);
        } else {
            return rqc.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId() {
        RequestContext rqc = RequestContext.getCurrentContext();
        rqc.addZuulRequestHeader(CORRELATION_ID, UUID.randomUUID().toString());
    }

    public String getServiceId() {
        RequestContext context = RequestContext.getCurrentContext();
        if (context.get("serviceId")==null) return "";
        return context.get("serviceId").toString();
    }
}
