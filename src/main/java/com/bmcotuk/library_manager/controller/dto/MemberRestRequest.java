package com.bmcotuk.library_manager.controller.dto;

import com.bmcotuk.library_manager.repository.model.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberRestRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private LocalDate membershipDate;

    public static Member toEntity(MemberRestRequest restRequest) {
        Member entity = new Member();
        entity.setName(restRequest.getName());
        entity.setEmail(restRequest.getEmail());
        entity.setMembershipDate(restRequest.getMembershipDate());
        return entity;
    }
}
