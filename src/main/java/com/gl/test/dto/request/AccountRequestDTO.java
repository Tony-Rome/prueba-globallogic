package com.gl.test.dto.request;

import com.gl.test.dto.generic.PhoneDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@NonNull
public class AccountRequestDTO {

    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;

}
