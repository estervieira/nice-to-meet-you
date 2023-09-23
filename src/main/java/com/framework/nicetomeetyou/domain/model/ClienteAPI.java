package com.framework.nicetomeetyou.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ClienteAPI {

    private long id;
    private String status;
    private String name;
    private boolean hasTypeAppropriation;
    private String group;

}
