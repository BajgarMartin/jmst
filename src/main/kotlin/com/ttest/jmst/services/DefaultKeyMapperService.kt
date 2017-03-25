package com.ttest.jmst.services

import com.ttest.jmst.model.Link
import com.ttest.jmst.model.repositories.LinkRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import javax.transaction.Transactional

/**
 * Created by bajga on 20.03.2017.
 */
@Component
class DefaultKeyMapperService : KeyMapperService {

    @Autowired
    lateinit var converter: KeyConverterService

    @Autowired
    lateinit var repo: LinkRepository

    @Transactional
    override fun add(link: String) =
            converter.idToKey(repo.save(Link(link)).id)

    override fun getLink(key: String) : KeyMapperService.Get {
        val result = repo.findOne(converter.keyToId(key))
        return if (result.isPresent) {
            KeyMapperService.Get.Link(result.get().text)
        } else {
            KeyMapperService.Get.NotFound(key)
        }
    }
}

