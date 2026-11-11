# IronFront Minecraft Mod

IronFront is a Fabric Minecraft mod for Minecraft 1.21.1 that adds grounded firearms, ammunition, fragmentation bombs, and stronger terrain-damaging explosions while preserving a vanilla-styled Minecraft look.

## Requirements

- Minecraft 1.21.1
- Fabric Loader 0.16.10 or newer
- Fabric API for Minecraft 1.21.1
- Java 21
- Gradle 8.x for building when the Gradle wrapper is unavailable

## Install

1. Install Minecraft 1.21.1.
2. Install Fabric Loader 0.16.10 or newer.
3. Install Fabric API for Minecraft 1.21.1.
4. Build the project with Java 21:
   ```sh
   ./gradlew build
   ```
5. If the Gradle wrapper is not present, use an installed Gradle 8.x distribution or generate a Gradle 8.x wrapper first.
6. Copy the generated `build/libs/frontier-warfare-1.0.0.jar` into the Minecraft `mods` folder.
7. Launch Minecraft with the Fabric profile.

## Use

The mod adds the following items:

- Field Rifle: fires a hitscan shot up to 64 blocks, damages the nearest living target in line of sight for 9 damage, consumes one Cartridge, has a 12-tick cooldown, and loses durability after firing.
- Cartridge: ammunition for the Field Rifle. Each crafting operation produces four Cartridges.
- Fragmentation Bomb: immediately detonates at the block surface being aimed at, or at the end of the 12-block targeting range when no block is hit. It creates a TNT-style terrain-damaging explosion with power 3.5.

Crafting recipes:

Field Rifle:
```text
  II
 SI
S
```
I = Iron Ingot
S = Stick

Cartridge:
```text
 I
IGI
 I
```
I = Iron Nugget
G = Gunpowder

Fragmentation Bomb:
```text
III
IGI
III
```
I = Iron Nugget
G = Gunpowder

Craft the items at a crafting table or obtain them from the Frontier Warfare creative tab. Hold the Field Rifle with Cartridges in your inventory, then use the rifle to fire. Hold a Fragmentation Bomb and use it while aiming at a nearby surface to detonate it. The mod does not add custom commands.

---

More Minecraft mod projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/00313e48-5f0f-4d92-b69c-6f994cef4638?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=00313e48-5f0f-4d92-b69c-6f994cef4638
