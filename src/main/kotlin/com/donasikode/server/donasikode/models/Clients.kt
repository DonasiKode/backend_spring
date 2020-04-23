package com.donasikode.server.donasikode.models

import lombok.Data
import javax.persistence.*

@Data
@Entity
@Table(uniqueConstraints= [UniqueConstraint(columnNames = ["accessKey"])])
data class Clients(
    @Id var id: Long,
    @Column(unique = true) var accessKey: String,
    var clientSecretKey: String,
    var clientName: String,
    var clientEmail: String
)