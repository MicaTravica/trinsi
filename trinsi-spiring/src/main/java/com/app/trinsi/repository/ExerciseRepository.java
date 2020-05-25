package com.app.trinsi.repository;

import com.app.trinsi.model.CATEGORY;
import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("select e from Exercise e where e.name like concat('%', ?1, '%') and (e.exerciseType = ?2 or ?2 = null)" +
            "and  (e.exerciseWeight = ?3 or ?3 = null)")
    Page<Exercise> search(String name, EXERCISE_TYPE exerciseType, CATEGORY exerciseWeight, Pageable pageable);
}
