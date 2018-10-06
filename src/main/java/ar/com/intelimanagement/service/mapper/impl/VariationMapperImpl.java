package ar.com.intelimanagement.service.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.intelimanagement.domain.Product;
import ar.com.intelimanagement.domain.SupervisorApprovals;
import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;
import ar.com.intelimanagement.service.dto.VariationDTO;
import ar.com.intelimanagement.service.mapper.ApprovalsMapper;
import ar.com.intelimanagement.service.mapper.ProductMapper;
import ar.com.intelimanagement.service.mapper.UserMapper;
import ar.com.intelimanagement.service.mapper.VariationMapper;

@Component
public class VariationMapperImpl implements VariationMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ApprovalsMapper approvalsMapper;
    
    @Override
    public List<Variation> toEntity(List<VariationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Variation> list = new ArrayList<Variation>( dtoList.size() );
        for ( VariationDTO variationDTO : dtoList ) {
            list.add( toEntity( variationDTO ) );
        }

        return list;
    }

    @Override
    public List<VariationDTO> toDto(List<Variation> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<VariationDTO> list = new ArrayList<VariationDTO>( entityList.size() );
        for ( Variation variation : entityList ) {
            list.add( toDto( variation ) );
        }

        return list;
    }

    @Override
    public VariationDTO toDto(Variation variation) {
        if ( variation == null ) {
            return null;
        }

        VariationDTO variationDTO = new VariationDTO();

        Long id = variationProductId( variation );
        if ( id != null ) {
            variationDTO.setProduct( id );
        }
        Long id1 = variationCreationUserId( variation );
        if ( id1 != null ) {
            variationDTO.setCreationUser( id1 );
        }
        variationDTO.setId( variation.getId() );
        variationDTO.setExtraCharge( variation.getExtraCharge() );
        variationDTO.setNewCharge( variation.getNewCharge() );
        variationDTO.setNewCost( variation.getNewCost() );
        variationDTO.setNewBenefit( variation.getNewBenefit() );
        variationDTO.setNewExternalLocatorId( variation.getNewExternalLocatorId() );
        variationDTO.setComments( variation.getComments() );
        variationDTO.setCreationDate( variation.getCreationDate() );
        variationDTO.setArea( variation.getArea() );
        variationDTO.setCampaing( variation.getCampaing() );
        variationDTO.setReason( variation.getReason() );
        variationDTO.setRecoverable( variation.getRecoverable() );
        variationDTO.setRefundInPoints( variation.getRefundInPoints() );
        variationDTO.setRefundInCash( variation.getRefundInCash() );
        variationDTO.setCacel( variation.getCacel() );

        return variationDTO;
    }

    @Override
    public Variation toEntity(VariationDTO variationDTO) {
        if ( variationDTO == null ) {
            return null;
        }

        Variation variation = new Variation();

        variation.setProduct( productMapper.fromId( variationDTO.getProduct() ) );
        variation.setCreationUser( userMapper.userFromId( variationDTO.getCreationUser() ) );
        variation.setApprovals(approvalsMapper.fromId(variationDTO.getApprovalsId()));
        variation.setId( variationDTO.getId() );
        variation.setExtraCharge( variationDTO.getExtraCharge() );
        variation.setNewCharge( variationDTO.getNewCharge() );
        variation.setNewCost( variationDTO.getNewCost() );
        variation.setNewBenefit( variationDTO.getNewBenefit() );
        variation.setNewExternalLocatorId( variationDTO.getNewExternalLocatorId() );
        variation.setComments( variationDTO.getComments() );
        variation.setCreationDate( variationDTO.getCreationDate() );
        variation.setArea( variationDTO.getArea() );
        variation.setCampaing( variationDTO.getCampaing() );
        variation.setReason( variationDTO.getReason() );
        variation.setRecoverable( variationDTO.getRecoverable() );
        variation.setRefundInPoints( variationDTO.getRefundInPoints() );
        variation.setRefundInCash( variationDTO.getRefundInCash() );
        variation.setCacel( variationDTO.getCacel() );
        return variation;
    }

    private Long variationProductId(Variation variation) {
        if ( variation == null ) {
            return null;
        }
        Product product = variation.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long variationCreationUserId(Variation variation) {
        if ( variation == null ) {
            return null;
        }
        User creationUser = variation.getCreationUser();
        if ( creationUser == null ) {
            return null;
        }
        Long id = creationUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
