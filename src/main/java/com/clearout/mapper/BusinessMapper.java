package com.clearout.mapper;

import com.clearout.dto.AddressDto;
import com.clearout.dto.BusinessDto;
import com.clearout.entity.Business;
import org.springframework.stereotype.Component;

@Component
public class BusinessMapper {

    public BusinessDto toBusinessDto(Business business) {
        return BusinessDto.builder()
                .companyName(business.getCompanyName())
                .representativeFirstname(business.getRepresentativeFirstname())
                .representativeLastname(business.getRepresentativeLastname())
                .representativeEmail(business.getRepresentativeEmail())
                .phoneNumber(business.getPhoneNo())
                .addressDto(new AddressDto(
                        business.getAddress().getHouseNo(),
                        business.getAddress().getStreet(),
                        business.getAddress().getLandmark(),
                        business.getAddress().getCity(),
                        business.getAddress().getState(),
                        business.getAddress().getCountry(),
                        business.getAddress().getZip()
                ))
                .totalEWasteRequest(business.getEWasteRequests()!=null?business.getEWasteRequests().size():0)
                .build();
    }
}
