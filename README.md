# quarkus-rest: Pragmatic REST API Starter

A clean, opinionated Quarkus setup built for small-to-medium backends — fast dev, low friction, strong conventions.

- ✅ Minimal boilerplate  
- ✅ Designed for **pragmatic teams** — fast-moving projects, prototype-to-prod  
- ✅ Emphasizes **JSON-first**, **typed query**, and **explicit mapping control**  
- ✅ Avoids overengineering (no premature hexagonal layering unless needed)  

---

## ⚙️ Tech Stack

- **Quarkus**: Supersonic, subatomic Java framework  
- **Jakarta REST**: Native REST endpoints  
- **Jackson**: JSON serialization  
- **PostgreSQL via JDBC**  
- *(Optional)*: `row_to_json` + Jackson mapping for frictionless data handling

---

## 🚀 Getting Started

### Dev Mode (live reload + Dev UI)

```bash
./mvnw quarkus:dev
