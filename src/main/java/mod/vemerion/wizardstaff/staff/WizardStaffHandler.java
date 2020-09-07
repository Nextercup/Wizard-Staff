package mod.vemerion.wizardstaff.staff;

import net.minecraftforge.items.ItemStackHandler;

public class WizardStaffHandler extends ItemStackHandler {
	
	private boolean isDirty = true;
	
	public boolean isDirty() {
		boolean dirty = isDirty;
		isDirty = false;
		return dirty;
	}

	@Override
    protected void onContentsChanged(int slot) {
		isDirty = true;
	}
}
