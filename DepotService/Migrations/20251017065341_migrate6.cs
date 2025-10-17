using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace DepotService.Migrations
{
    /// <inheritdoc />
    public partial class migrate6 : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_ComptesDepot_Numero",
                table: "ComptesDepot",
                column: "Numero",
                unique: true);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_ComptesDepot_Numero",
                table: "ComptesDepot");
        }
    }
}
