package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.ReceiptDetails;
import exercise.gameongradle.rest.resourcesdto.SimplifiedReceiptDetailsDto;
import exercise.gameongradle.service.createdto.ReceiptDetailsDto;
import exercise.gameongradle.service.restdto.ReceiptDetailsRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReceiptDetailsMapper extends EntityMapper<ReceiptDetailsRestDto, ReceiptDetails, ReceiptDetailsDto> {
    @Mapping(source = "receipt.id", target = "receiptId")
    @Mapping(source = "id", target = "receiptDetailsId")
    @Mapping(source = "gameCode.id", target = "gameCodeId")
    SimplifiedReceiptDetailsDto toSimplifiedDto(ReceiptDetails receiptDetails);
}
