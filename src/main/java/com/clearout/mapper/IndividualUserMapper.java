package com.clearout.mapper;

import com.clearout.dto.AddressDto;
import com.clearout.dto.IndividualUserDto;
import com.clearout.entity.IndividualUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndividualUserMapper {

    public IndividualUserDto toIndividualUser(IndividualUser individualUser) {
        return IndividualUserDto.builder()
                .firstname(individualUser.getFirstname())
                .lastname(individualUser.getLastname())
                .email(individualUser.getEmail())
                .phoneNumber(individualUser.getPhoneNo())
                .addressDto(new AddressDto(
                        individualUser.getAddress().getHouseNo(),
                        individualUser.getAddress().getStreet(),
                        individualUser.getAddress().getLandmark(),
                        individualUser.getAddress().getCity(),
                        individualUser.getAddress().getState(),
                        individualUser.getAddress().getCountry(),
                        individualUser.getAddress().getZip()
                ))
                .totalRepairRequest(individualUser.getRepairRequests() != null ?individualUser.getRepairRequests().size() : 0)
                .totalEWasteRequest(individualUser.getEWasteRequests() != null ?individualUser.getEWasteRequests().size() : 0)
                .totalProductSold(individualUser.getSellingProducts()  != null ?individualUser.getSellingProducts().size() : 0)
                .totalBoughtProduct(individualUser.getPurchasedProducts() != null ?individualUser.getPurchasedProducts().size() : 0)
                .build();
    }
}
