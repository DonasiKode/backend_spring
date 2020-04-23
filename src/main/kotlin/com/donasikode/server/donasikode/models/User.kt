package com.donasikode.server.donasikode.models

import lombok.Data
import javax.persistence.*

@Data
@Entity
@Table(uniqueConstraints= [UniqueConstraint(columnNames = ["username", "email"])])
data class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long,
    @Column(unique = true) var username: String,
    @Column var password: String,
    @Column var fullname: String,
    @Column(unique = true) var email: String,
    @Column var phone: String?,
    @Column var role: String? = null,
    @Column var addTime: Long? = null,
    @Column var modifyTime: Long? = null,
    @Column var active: Boolean? = null
)