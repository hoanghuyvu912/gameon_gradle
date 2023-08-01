package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.Publisher;
import exercise.gameongradle.service.createdto.PublisherDto;
import exercise.gameongradle.service.restdto.PublisherRestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper extends EntityMapper<PublisherRestDto, Publisher, PublisherDto>{
}
