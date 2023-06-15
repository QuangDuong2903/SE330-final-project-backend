//package com.quangduong.SE330backend.repository.elastic;
//
//import com.quangduong.SE330backend.model.UserModel;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//public interface UserModelRepository extends ElasticsearchRepository<UserModel, Long> {
//    @Query("{\"bool\": {\"should\": [{\"match\": {\"displayName\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}},{\"match_phrase_prefix\": {\"email\": {\"query\": \"?0\"}}}]}}")
//    List<UserModel> findUserByEmailOrDisplayName(String query, Pageable pageable);
//}
