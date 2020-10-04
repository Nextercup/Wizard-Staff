package mod.vemerion.wizardstaff;

import mod.vemerion.wizardstaff.Magic.Magics;
import mod.vemerion.wizardstaff.renderer.WizardStaffTileEntityRenderer;
import mod.vemerion.wizardstaff.staff.WizardStaffItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {

	@SubscribeEvent
	public static void wizardStaff(RenderHandEvent event) {
		AbstractClientPlayerEntity player = Minecraft.getInstance().player;
		ItemStack itemStack = event.getItemStack();
		Item item = itemStack.getItem();
		float partialTicks = event.getPartialTicks();
		if (item instanceof WizardStaffItem && player.getActiveItemStack().equals(itemStack)) {
			event.setCanceled(true);
			Item magic = WizardStaffItem.getMagic(itemStack).getItem();
			HandSide side = event.getHand() == Hand.MAIN_HAND ? player.getPrimaryHand()
					: player.getPrimaryHand().opposite();
			WizardStaffTileEntityRenderer renderer = (WizardStaffTileEntityRenderer) item
					.getItemStackTileEntityRenderer();
			int maxDuration = itemStack.getUseDuration();
			float duration = (float) maxDuration - ((float) player.getItemInUseCount() - partialTicks + 1.0f);
			Magics.getInstance().get(magic).renderer().render(renderer, duration, maxDuration, itemStack,
					event.getMatrixStack(), event.getBuffers(), event.getLight(), OverlayTexture.NO_OVERLAY,
					partialTicks, side);
		}
	}
}
