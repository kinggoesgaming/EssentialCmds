/*
 * This file is part of EssentialCmds, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015 - 2015 HassanS6000
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.hsyyid.essentialcmds.cmdexecutors;

import io.github.hsyyid.essentialcmds.internal.CommandExecutorBase;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import javax.annotation.Nonnull;
import java.util.Optional;

public class SpeedExecutor extends CommandExecutorBase
{
	@Override
	public CommandResult execute(CommandSource src, CommandContext ctx) throws CommandException
	{
		Optional<Player> optionalTarget = ctx.<Player> getOne("player");
		int multiplier = ctx.<Integer> getOne("speed").get();

		if (!optionalTarget.isPresent())
		{
			if (src instanceof Player)
			{
				Player player = (Player) src;
				multiplier = Math.min(multiplier, 20);

				if (player.get(Keys.IS_FLYING).isPresent() && player.get(Keys.IS_FLYING).get())
				{
					double flySpeed = 0.05d * multiplier;
					player.offer(Keys.FLYING_SPEED, flySpeed);
					src.sendMessage(Text.of(TextColors.GREEN, "Success! ", TextColors.YELLOW, "Your flying speed has been updated."));
				}
				else
				{
					double walkSpeed = 0.1d * multiplier;
					player.offer(Keys.WALKING_SPEED, walkSpeed);
					src.sendMessage(Text.of(TextColors.GREEN, "Success! ", TextColors.YELLOW, "Your walking speed has been updated."));
				}
			}
			else
			{
				src.sendMessage(Text.of(TextColors.DARK_RED, "Error!", TextColors.RED, "You must be a player to do /speed"));
			}
		}
		else if(src.hasPermission("essentialcmds.speed.others"))
		{
			Player player = optionalTarget.get();
			multiplier = Math.min(multiplier, 20);

			if (player.get(Keys.IS_FLYING).isPresent() && player.get(Keys.IS_FLYING).get())
			{
				double flySpeed = 0.05d * multiplier;
				player.offer(Keys.FLYING_SPEED, flySpeed);
				player.sendMessage(Text.of(TextColors.GREEN, "Success! ", TextColors.YELLOW, "Your flying speed has been updated."));
			}
			else
			{
				double walkSpeed = 0.1d * multiplier;
				player.offer(Keys.WALKING_SPEED, walkSpeed);
				player.sendMessage(Text.of(TextColors.GREEN, "Success! ", TextColors.YELLOW, "Your walking speed has been updated."));
			}
			
			src.sendMessage(Text.of(TextColors.GREEN, "Success! ", TextColors.YELLOW, "Updated player's speed."));
		}

		return CommandResult.success();
	}

	@Nonnull
	@Override
	public String[] getAliases() {
		return new String[] { "speed" };
	}

	@Nonnull
	@Override
	public CommandSpec getSpec() {
		return CommandSpec.builder().description(Text.of("Speed Command")).permission("essentialcmds.speed.use")
				.arguments(GenericArguments.seq(
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("speed"))),
						GenericArguments.optional(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))))))
				.executor(this).build();
	}
}
