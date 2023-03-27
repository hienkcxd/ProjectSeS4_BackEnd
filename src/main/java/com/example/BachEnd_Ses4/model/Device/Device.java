package com.example.BachEnd_Ses4.model.Device;


import lombok.*;

import javax.persistence.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String deviceID;
    @Column(unique = true, nullable = false)
    private String deviceName;
    private String username;
    private String storeName;
    private String groupName;
    private String area;
    private String fileName;
}
