using Microsoft.EntityFrameworkCore;
using DepotService.Models;

namespace DepotService.Data
{
    public class DepotContext : DbContext
    {
        public DepotContext(DbContextOptions<DepotContext> options) : base(options) { }

        public DbSet<DepotAccount> Depots { get; set; }
    }
}