package com.auth.springreact.exception;

import tools.jackson.databind.node.StringNode;

public class UserCreationException extends RuntimeException{
    public UserCreationException(String message){
        super(message);
    }
}
