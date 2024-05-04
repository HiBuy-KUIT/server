package hibuy.server.service;

import hibuy.server.domain.Sample;
import hibuy.server.dto.sample.GetSampleResponse;
import hibuy.server.dto.sample.PostSampleRequest;
import hibuy.server.repository.SampleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SampleServiceTest {

    @Autowired SampleService sampleService;
    @Autowired SampleRepository sampleRepository;

    @AfterEach
    void tearDown() {
        sampleRepository.deleteAllInBatch();
    }

    @DisplayName("새로 등록한 샘플의 id를 반환한다.")
    @Test
    void addSample() {
        //given
        String productName = "락토핏";
        String companyName = "종근당";
        String imageUrl = "aaa@aaa.com";
        String productUrl = "bbb@bbb.com";

        Sample sample = Sample.builder()
                .productName(productName)
                .companyName(companyName)
                .imageUrl(imageUrl)
                .productUrl(productUrl)
                .build();

        sampleRepository.save(sample);

        PostSampleRequest request = new PostSampleRequest(productName, companyName, imageUrl, productUrl);

        //when
        Long sampleId = sampleService.addSample(request);

        //then
        assertThat(sampleId).isEqualTo(sample.getSampleId() + 1);
    }

    @DisplayName("샘플의 조회 결과를 반환한다.")
    @Test
    void getSamples() {
        //given
        Sample sample = Sample.builder()
                .productName("락토핏")
                .companyName("종근당")
                .imageUrl("aaa@aaa.com")
                .productUrl("bbb@bbb.com")
                .build();

        Sample sample2 = Sample.builder()
                .productName("락토핏2")
                .companyName("종근당")
                .imageUrl("aaa2@aaa.com")
                .productUrl("bbb2@bbb.com")
                .build();

        sampleRepository.saveAll(List.of(sample, sample2));

        //when
        List<GetSampleResponse> samples = sampleService.getSamples();

        //then
        assertThat(samples).hasSize(2)
                .extracting("productName", "companyName", "imageUrl", "productUrl")
                .containsExactlyInAnyOrder(
                        tuple("락토핏", "종근당", "aaa@aaa.com", "bbb@bbb.com"),
                        tuple("락토핏2", "종근당", "aaa2@aaa.com", "bbb2@bbb.com")
                );
    }

}