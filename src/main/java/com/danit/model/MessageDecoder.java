package com.danit.model;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

    public static Gson gson = new Gson();

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public Message decode(final String s) throws DecodeException {
        return gson.fromJson(s,Message.class);
    }

    @Override
    public boolean willDecode(final String s) {
        return s!=null;
    }

}
