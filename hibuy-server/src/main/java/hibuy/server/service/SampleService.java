package hibuy.server.service;

import hibuy.server.domain.Sample;
import hibuy.server.dto.sample.GetSampleResponse;
import hibuy.server.dto.sample.PostSampleRequest;
import hibuy.server.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    public void addSample(PostSampleRequest postSampleRequest) {

        log.debug("[SampleService.addSample]");

        Sample sample = new Sample(postSampleRequest.getProductName(),
                postSampleRequest.getCompanyName(),
                postSampleRequest.getImageUrl(),
                postSampleRequest.getProductUrl());

        sampleRepository.save(sample);

    }

    public List<GetSampleResponse> getSamples() {

        log.debug("[SampleService.addSample]");

        return sampleRepository.findAll().stream()
                .map(sample -> new GetSampleResponse(
                        sample.getProductName(),
                        sample.getCompanyName(),
                        sample.getImageUrl(),
                        sample.getProductUrl()
                )).toList();

    }
}
