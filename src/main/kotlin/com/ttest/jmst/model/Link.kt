package com.ttest.jmst.model

import javax.persistence.*

/**
 * Created by bajga on 20.03.2017.
 */
@Entity
@Table(name="Links")
data class Link (
    var text: String = "",
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "links_sequence")
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_seq")
    var id: Long = 0
)