using DepotService.Data;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Ajouter DbContext (SQL Server ou PostgreSQL)
builder.Services.AddDbContext<DepotContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("DepotDb")));
// Pour SQL Server remplacer par : options.UseSqlServer(...)

builder.Services.AddControllers();

var app = builder.Build();

app.MapControllers();

app.Run();
