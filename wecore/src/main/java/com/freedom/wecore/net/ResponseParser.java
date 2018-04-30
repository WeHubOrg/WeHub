package com.freedom.wecore.net;

import com.google.gson.JsonElement;

/**
 * @author vurtne on 30-Apr-18.
 * @param <T>
 */
public interface ResponseParser<T> {

	T parse(JsonElement je);

}