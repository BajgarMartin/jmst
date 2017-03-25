package com.ttest.jmst.model.repositories

import com.ttest.jmst.model.Link
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.Repository
import java.util.*

/**
 * Created by bajga on 20.03.2017.
 */
interface LinkRepository : Repository<Link, Long> {
    fun findOne(id: Long?) : Optional<Link>
    fun save(link: Link) : Link
    fun findAll() : List<Link>
}