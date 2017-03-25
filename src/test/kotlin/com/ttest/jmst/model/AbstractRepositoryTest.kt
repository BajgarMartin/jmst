package com.ttest.jmst.model

import com.github.springtestdbunit.DbUnitTestExecutionListener
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests

/**
 * Created by bajga on 20.03.2017.
 */
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@TestExecutionListeners(DbUnitTestExecutionListener::class)
@SpringBootTest()
@DirtiesContext
abstract class AbstractRepositoryTest: AbstractTransactionalJUnit4SpringContextTests() {

}