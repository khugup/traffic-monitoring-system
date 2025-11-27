package com.example.traffic.model;


public enum CongestionLevel {
NORMAL(1),
HIGH(2),
VERY_HIGH(3);
private final int value;

CongestionLevel(int value) {
this.value = value;
}


public int getValue() {
return value;
}
}
