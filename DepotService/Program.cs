using BanqueDepot.Data;
using BanqueDepot.Services;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Ajouter DbContext (PostgreSQL ou SQL Server)
builder.Services.AddDbContext<BanqueDepotContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("DepotDb")));

// Injection de dépendance du service
builder.Services.AddScoped<CompteDepotService>();

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Middleware Swagger
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.MapControllers();

app.Run();
