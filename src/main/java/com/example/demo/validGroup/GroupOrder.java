package com.example.demo.validGroup;

import jakarta.validation.GroupSequence;

//ほんとはこれいれたい
//import javax.validation.groupsequence

@GroupSequence({ValidGroup1.class,ValidGroup2.class})
public interface GroupOrder {

}
