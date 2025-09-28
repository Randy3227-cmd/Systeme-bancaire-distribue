using BanqueDepot.Data;
using BanqueDepot.Services;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Ajouter DbContext (PostgreSQL ou SQL Server)
builder.Services.AddDbContext<BanqueDepotContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("BanqueDepotDb")));

// Injection de dépendance du service
builder.Services.AddScoped<CompteDepotService>();

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

using (var scope = app.Services.CreateScope())
{
    var db = scope.ServiceProvider.GetRequiredService<BanqueDepotContext>();
    
    // Crée la base et les tables si elles n'existent pas
    db.Database.EnsureCreated(); 
    
    // OU : applique toutes les migrations (recommandé en production)
    db.Database.Migrate();
}
// Middleware Swagger
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.MapControllers();

app.Run();
