import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTrigger,
} from "@/components/ui/dialog";
import CreateProjectForm from "../Project/CreateProjectForm";
import {DropdownMenuTrigger, DropdownMenu, DropdownMenuContent, DropdownMenuItem } from "@/components/ui/dropdown-menu";
import { PersonIcon } from "@radix-ui/react-icons";
import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate=useNavigate()
  return (
    <div className="border-b py-4 px-5 flex item-center justify-between">
      <div className="flex items-center gap-3">
        <p onClick={()=>navigate("/")} className="cursor-pointer">Projexio</p>

        <Dialog>
          <DialogTrigger>
            <Button variant="ghost">New Project</Button>
          </DialogTrigger>

          <DialogContent>
          <DialogHeader>Create New Project</DialogHeader>
            <CreateProjectForm/>
          </DialogContent>
        </Dialog>

        <Button varaint="ghost">Upgrade Plan</Button>
      </div>

      <div className="flex gap-3 items-center ">
        <DropdownMenu>
        <DropdownMenuTrigger>
            <Button varaint="outline" size="icon" className="rounded-full border-gray-500">
                <PersonIcon/>
            </Button>
        </DropdownMenuTrigger>

        <DropdownMenuContent>
            <DropdownMenuItem>Logout</DropdownMenuItem>
        </DropdownMenuContent>

        </DropdownMenu>
        <p>Aakash Shahi</p>

      </div>
    </div>
  );
};

export default Navbar;
