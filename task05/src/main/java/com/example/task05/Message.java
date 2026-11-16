package com.example.task05;

import java.util.*;
import java.util.function.Consumer;

interface Message<T> {
    String getFrom();
    String getTo();
    T getContent();
}