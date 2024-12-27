package com.wellsfargo.counselor.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Advisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advisorId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    // One advisor can have many clients
    @OneToMany(mappedBy = "advisor")
    private List<Client> clients;

    public Advisor() { }

    public Advisor(String firstName, String lastName, String address, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public Long getAdvisorId() {
        return advisorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Entity
    public static class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long clientId;

        @Column(nullable = false)
        private String firstName;

        @Column(nullable = false)
        private String lastName;

        @Column(nullable = false)
        private String address;

        @Column(nullable = false)
        private String phone;

        @Column(nullable = false)
        private String email;

        // Many clients belong to one advisor
        @ManyToOne
        @JoinColumn(name = "advisorId")
        private Advisor advisor;

        // One client can have many portfolios
        @OneToMany(mappedBy = "client")
        private List<Portfolio> portfolios;

        public Client() { }

        public Client(String firstName, String lastName, String address, String phone, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.phone = phone;
            this.email = email;
        }

        // Getters and Setters
        public Long getClientId() {
            return clientId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Advisor getAdvisor() {
            return advisor;
        }

        public void setAdvisor(Advisor advisor) {
            this.advisor = advisor;
        }

        public List<Portfolio> getPortfolios() {
            return portfolios;
        }

        public void setPortfolios(List<Portfolio> portfolios) {
            this.portfolios = portfolios;
        }

        @Entity
        public static class Portfolio {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long portfolioId;

            @Column(nullable = false)
            private LocalDate creationDate;

            // Many portfolios belong to one client
            @ManyToOne
            @JoinColumn(name = "clientId")
            private Client client;

            // One portfolio can have many securities
            @OneToMany(mappedBy = "portfolio")
            private List<Security> securities;

            public Portfolio() { }

            public Portfolio(LocalDate creationDate) {
                this.creationDate = creationDate;
            }

            // Getters and Setters
            public Long getPortfolioId() {
                return portfolioId;
            }

            public LocalDate getCreationDate() {
                return creationDate;
            }

            public void setCreationDate(LocalDate creationDate) {
                this.creationDate = creationDate;
            }

            public Client getClient() {
                return client;
            }

            public void setClient(Client client) {
                this.client = client;
            }

            public List<Security> getSecurities() {
                return securities;
            }

            public void setSecurities(List<Security> securities) {
                this.securities = securities;
            }

            @Entity
            public static class Security {

                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                private Long securityId;

                @Column(nullable = false)
                private String name;

                @Column(nullable = false)
                private String category;

                @Column(nullable = false)
                private Float purchasePrice;

                @Column(nullable = false)
                private LocalDate purchaseDate;

                @Column(nullable = false)
                private Integer quantity;

                // Many securities belong to one portfolio
                @ManyToOne
                @JoinColumn(name = "portfolioId")
                private Portfolio portfolio;

                public Security() { }

                public Security(String name, String category, Float purchasePrice, LocalDate purchaseDate, Integer quantity) {
                    this.name = name;
                    this.category = category;
                    this.purchasePrice = purchasePrice;
                    this.purchaseDate = purchaseDate;
                    this.quantity = quantity;
                }

                // Getters and Setters
                public Long getSecurityId() {
                    return securityId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public Float getPurchasePrice() {
                    return purchasePrice;
                }

                public void setPurchasePrice(Float purchasePrice) {
                    this.purchasePrice = purchasePrice;
                }

                public LocalDate getPurchaseDate() {
                    return purchaseDate;
                }

                public void setPurchaseDate(LocalDate purchaseDate) {
                    this.purchaseDate = purchaseDate;
                }

                public Integer getQuantity() {
                    return quantity;
                }

                public void setQuantity(Integer quantity) {
                    this.quantity = quantity;
                }

                public Portfolio getPortfolio() {
                    return portfolio;
                }

                public void setPortfolio(Portfolio portfolio) {
                    this.portfolio = portfolio;
                }
            }
        }
    }
}
