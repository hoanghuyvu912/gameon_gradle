package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.Receipt;
import exercise.gameongradle.service.createdto.ReceiptDto;
import exercise.gameongradle.service.customDto.ReceiptResponseDto;
import exercise.gameongradle.service.restdto.ReceiptRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceiptMapper extends EntityMapper<ReceiptRestDto, Receipt, ReceiptDto>{
    List<ReceiptResponseDto> toResponseDtos(List<Receipt> receipts);
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.username")
    ReceiptResponseDto toResponseDto(Receipt receipt);
}
